package com.example.aircraftWar.application.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Build;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.example.aircraftWar.R;
import com.example.aircraftWar.activity.ResultActivity;
import com.example.aircraftWar.activity.MainActivity;
import com.example.aircraftWar.aircraft.AbstractEnemyAircraft;
import com.example.aircraftWar.aircraft.BossEnemy;
import com.example.aircraftWar.aircraft.EliteEnemy;
import com.example.aircraftWar.aircraft.HeroAircraft;
import com.example.aircraftWar.aircraft.MobEnemy;
import com.example.aircraftWar.application.ImageManager;
import com.example.aircraftWar.application.music.MusicService;
import com.example.aircraftWar.application.music.SoundManager;
import com.example.aircraftWar.application.music.SoundMap;
import com.example.aircraftWar.basic.AbstractFlyingObject;
import com.example.aircraftWar.bullet.BaseBullet;
import com.example.aircraftWar.bullet.EnemyBullet;
import com.example.aircraftWar.bullet.HeroBullet;
import com.example.aircraftWar.factory.enemyFactory.EliteEnemyFactory;
import com.example.aircraftWar.factory.enemyFactory.EnemyFactory;
import com.example.aircraftWar.factory.enemyFactory.MobEnemyFactory;
import com.example.aircraftWar.observer.AbstractSubscriber;
import com.example.aircraftWar.prop.AbstractProp;
import com.example.aircraftWar.prop.BloodProp;
import com.example.aircraftWar.prop.BombProp;
import com.example.aircraftWar.prop.BulletProp;
import com.example.aircraftWar.prop.GodProp;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public abstract class Game extends SurfaceView implements SurfaceHolder.Callback,Runnable{
    private int backGroundTop = 0;
    //画布等
    boolean mbLoop = false; //控制绘画线程的标志位
    private SurfaceHolder mSurfaceHolder;
    private Canvas canvas;  //绘图的画布
    private Paint mPaint;

    /**
     * Scheduled 线程池，用于任务调度
     */
    private final ScheduledExecutorService executorService;

    /**
     * 时间间隔(ms)，控制刷新频率
     */
    private int timeInterval = 40;

    private  HeroAircraft heroAircraft;
    private  List<AbstractEnemyAircraft> enemyAircrafts;
    private  List<BaseBullet> heroBullets;
    private  List<BaseBullet> enemyBullets;
    private  List<AbstractProp> props;
    private  List<AbstractSubscriber> subscribers;
    private  GodProp godProp;

    private  EnemyFactory enemyFactory;

    double  enemyMaxNumber;

    /**
     * eliteEnemyProbability 每次产生敌机为精英敌机的概率
     * lastBossScore 上一架boss机被击败时的分数
     */
    float eliteEnemyProbability;
    private int lastBossScore = 0;
    int bossScoreThreshold;
    boolean BossExist=false;
    private boolean gameOverFlag = false;
    private static int score = 0;

    int time = 0;

    /**
     * 周期（ms)
     * 敌机射击的周期(ms)
     * 指示子弹的发射、敌机的产生频率
     */
    private int cycleDuration = 600;
    int enemyShootDuration;
    private int cycleTime = 0;
    private int enemyShootCycleTime = 0;

    //英雄机的位置
    public float x;
    public float y;

    //音乐操作
    Intent intent;
    public boolean musicOn=true;
    public MediaPlayer mediaPlayer;
    public Context context;

    public Game(Context context) {
        super(context);
        BossExist=false;
        mbLoop = true;
        mPaint = new Paint();  //设置画笔
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        this.context= context;
        this.setFocusable(true);

        loading_img();//加载图片
        //加载声音映射
        loadSound();
        initMap();

        if(musicOn){
            intent = new Intent(context,MusicService.class);
            intent.putExtra("action","play");
            intent.putExtra("bgmId",R.raw.bgm);
            context.startService(intent);
        }

        heroAircraft = HeroAircraft.getHeroAircraft();

        //英雄机位置初始化
        x = MainActivity.screenWidth/2;
        y = MainActivity.screenHeight + 3*ImageManager.HERO_IMAGE.getHeight();

        enemyAircrafts = new LinkedList<>();
        heroBullets = new LinkedList<>();
        enemyBullets = new LinkedList<>();
        props = new LinkedList<>();
        subscribers = new LinkedList<>();

        initializationDifficulty();

        ThreadFactory gameThread = new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread t = new Thread(r);
                t.setName("game thread");
                return t;
            }
        };
        //Scheduled 线程池，用于定时任务调度
        executorService = new ScheduledThreadPoolExecutor(1);
       }

    public void initMap(){
        SoundMap.soundMap.put("bgm",R.raw.bgm);
        SoundMap.soundMap.put("bgm_boss",R.raw.bgm_boss);
        SoundMap.soundMap.put("bomb_explosion",R.raw.bomb_explosion);
        SoundMap.soundMap.put("bullet",R.raw.bullet);
        SoundMap.soundMap.put("bullet_hit",R.raw.bullet_hit);
        SoundMap.soundMap.put("game_over",R.raw.game_over);
        SoundMap.soundMap.put("get_supply",R.raw.get_supply);
    }
    public void loadSound(){
        SoundManager.soundMap.put(R.raw.bullet, SoundManager.soundPool.load(getContext(),R.raw.bullet,0));
        SoundManager.soundMap.put(R.raw.bomb_explosion,SoundManager.soundPool.load(getContext(),R.raw.bomb_explosion,0));
        SoundManager.soundMap.put(R.raw.bullet_hit,SoundManager.soundPool.load(getContext(),R.raw.bullet_hit,0));
        SoundManager.soundMap.put(R.raw.get_supply,SoundManager.soundPool.load(getContext(),R.raw.get_supply,0));
        SoundManager.soundMap.put(R.raw.game_over,SoundManager.soundPool.load(getContext(),R.raw.game_over,0));
        SoundManager.soundMap.put(R.raw.bgm_boss,SoundManager.soundPool.load(getContext(),R.raw.bgm_boss,0));
        SoundManager.soundMap.put(R.raw.bgm,SoundManager.soundPool.load(getContext(),R.raw.bgm,0));
    }
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        executorService.scheduleWithFixedDelay(this, timeInterval, timeInterval, TimeUnit.MILLISECONDS);
    }
    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        MainActivity.screenWidth = width;
        MainActivity.screenHeight = height;
    }
    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
         mbLoop = false;
    }

    /**
     * 游戏难度初始化，包括：
     * 最大敌机数
     * 敌机属性：包括速度、血量
     * 精英敌机产生概率
     * boss机阈值，若能产生boss
     * boss机血量，若能产生boss
     */
    abstract void initializationDifficulty() ;

    /**
     * 游戏启动入口，执行游戏逻辑
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void action() {

        // 定时任务：绘制、对象产生、碰撞判定、击毁及结束判定
        Runnable task = () -> {

            time += timeInterval;

            //如需要，提升游戏难度
            if (increaseDifficultyHook()) {
                increaseDifficulty();
            }

            // 周期性执行（控制频率）
            if (timeCountAndNewCycleJudge()) {
                System.out.println(time);
                // 新敌机产生
                createNewEnemy();
                // 英雄机射击
                heroBullets.addAll(heroAircraft.shoot());
            }

            enemyShootAction();

            // 子弹移动
            bulletsMoveAction();

            // 飞机移动
            aircraftsMoveAction();

            //道具移动
            propMoveAction();

            // 撞击检测
            crashCheckAction();

            // 检测是否点击积分道具
            checkCreditPropUse();

            // 后处理
            postProcessAction();

            //每个时刻重绘界面
            draw();

            // 游戏结束检查
            if (heroAircraft.getHp() <= 0) {
                // 游戏结束
                executorService.shutdown();
                gameOverFlag = true;

                if(musicOn) {
                    intent.putExtra("action","stop");
                    intent.putExtra("resId",R.raw.game_over);
                    context.startService(intent);
                }
                System.out.println("Game Over!");

                activityChange();
            }

        };


        /**
         * 以固定延迟时间进行执行
         * 本次任务执行完成后，需要延迟设定的延迟时间，才会执行新的任务
         */
        executorService.scheduleWithFixedDelay(task, timeInterval, timeInterval, TimeUnit.MILLISECONDS);

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        action();
    }

    //***********************
    //      Action 各部分
    //***********************

    //游戏页面跳转
    private void activityChange() {
        intent = new Intent();
        intent.setClass(context, ResultActivity.class);
        intent.putExtra("score",score);
        context.startActivity(intent);
    }

    //检测积分道具是否使用
    private void checkCreditPropUse () {
        if (x > MainActivity.screenWidth - ImageManager.GOD_PROP_IMAGE.getWidth()
                && y >  MainActivity.screenHeight - ImageManager.GOD_PROP_IMAGE.getHeight()) {
            godProp = new GodProp();
            godProp.effect();
        }
    }

    private boolean timeCountAndNewCycleJudge() {
        cycleTime += timeInterval;
        if (cycleTime >= cycleDuration && cycleTime - timeInterval < cycleTime) {
            // 跨越到新的周期
            cycleTime %= cycleDuration;
            return true;
        } else {
            return false;
        }
    }

    private boolean enemyShootCycleJudge() {
        enemyShootCycleTime += timeInterval;
        if (enemyShootCycleTime >= enemyShootDuration && enemyShootCycleTime - timeInterval < enemyShootCycleTime) {
            // 跨越到新的周期
            enemyShootCycleTime %= enemyShootDuration;
            return true;
        } else {
            return false;
        }
    }

    private void enemyShootAction() {

        if (enemyShootCycleJudge()) {
            for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
                enemyBullets.addAll(enemyAircraft.shoot());
            }
        }
    }

    private void bulletsMoveAction() {
        for (BaseBullet bullet : heroBullets) {
            bullet.forward();
        }
        for (BaseBullet bullet : enemyBullets) {
            bullet.forward();
        }
    }

    private void aircraftsMoveAction() {
        for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
            enemyAircraft.forward();
        }
    }

    private void propMoveAction() {
        //道具移动
        for (AbstractProp prop : props) {
            prop.forward();
        }
    }


    /**
     * 碰撞检测：
     * 1. 敌机攻击英雄
     * 2. 英雄攻击/撞击敌机
     * 3. 英雄获得补给
     */
    private void crashCheckAction() {

        for (BaseBullet bullet : enemyBullets) {
            if (bullet.notValid()) {
                // 已被其他子弹击毁的敌机，不再检测
                // 避免多个子弹重复击毁同一敌机的判定
                continue;
            }
            if (heroAircraft.crash(bullet)) {
                // 英雄机撞击到敌机子弹
                // 英雄机损失一定生命值
                if (!GodProp.isGodEffect()) {
                    heroAircraft.decreaseHp(bullet.getPower());
                }
                bullet.vanish();
                if (heroAircraft.notValid()) {
                    //英雄机坠毁
                    break;
                }
            }
        }

        // 英雄子弹攻击敌机
        for (BaseBullet bullet : heroBullets) {
            if (bullet.notValid()) {
                continue;
            }
            for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
                if (enemyAircraft.notValid()) {
                    // 已被其他子弹击毁的敌机，不再检测
                    // 避免多个子弹重复击毁同一敌机的判定
                    continue;
                }
                if (enemyAircraft.crash(bullet)) {
                    // 敌机撞击到英雄机子弹
                    // 敌机损失一定生命值
                   if(musicOn) {
                        intent.putExtra("action","playsound");
                        intent.putExtra("resId",R.raw.bullet_hit);
                        context.startService(intent);
                    }

                    enemyAircraft.decreaseHp(bullet.getPower());
                    bullet.vanish();
                    if (enemyAircraft.notValid()) {
                        // 获得分数，产生道具补给
                        score += 10;
                        props.addAll(enemyAircraft.dropProp());
                        //记录击败boss机时刻的分数
                        if (enemyAircraft instanceof BossEnemy) {
                            BossEnemy.setBossExist(false);
                            if(musicOn){
                                intent = new Intent(context,MusicService.class);
                                intent.putExtra("action","change");
                                intent.putExtra("bgmId",R.raw.bgm);
                                context.startService(intent);
                            }
                            lastBossScore = score;
                        }
                    }
                }
            }
        }

        // 英雄机 与 敌机 相撞，均损毁
        for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
            if (enemyAircraft.crash(heroAircraft) || heroAircraft.crash(enemyAircraft)) {
                enemyAircraft.vanish();
                heroAircraft.decreaseHp(Integer.MAX_VALUE);
            }
        }

        for (AbstractProp prop : props) {
            if (prop.notValid()) {
                continue;
            }
            if (heroAircraft.crash(prop)) {
                // 道具生效
                if (prop instanceof BombProp) {
                    changeSubscribers((BombProp) prop);
                    if(musicOn){
                        intent.putExtra("action","playsound");
                        intent.putExtra("resId",R.raw.bomb_explosion);
                        context.startService(intent);
                    }
                }else if(prop instanceof BloodProp){
                    if(musicOn){

                        intent.putExtra("action","playsound");
                        intent.putExtra("resId",R.raw.get_supply);
                        context.startService(intent);
                    }
                }else if(prop instanceof BulletProp){
                    if(musicOn){
                        intent.putExtra("action","playsound");
                        intent.putExtra("resId",R.raw.bullet);
                        context.startService(intent);
                    }
                }
                prop.effect();
                prop.vanish();
            }
        }
    }


    private void createNewEnemy() {
        if (enemyAircrafts.size() < enemyMaxNumber ) {
            if (creatBossHook() & !BossEnemy.isBossExist() & score - lastBossScore >= bossScoreThreshold) {
                enemyFactory = createBoss();
                if(musicOn){
                    intent = new Intent(context,MusicService.class);
                    intent.putExtra("action","change");
                    intent.putExtra("bgmId",R.raw.bgm_boss);
                    context.startService(intent);
                }
            } else {
                if (Math.random() < eliteEnemyProbability) {
                    enemyFactory = new EliteEnemyFactory();
                }
                else {
                    enemyFactory = new MobEnemyFactory();
                }
            }
            enemyAircrafts.add(enemyFactory.creatEnemyAircraft());
        }
    }

    private void changeSubscribers(BombProp bombProp) {
        //将新的订阅者先转移至列表
        for (AbstractEnemyAircraft enemyAircraft : enemyAircrafts) {
            if ((!enemyAircraft.notValid()) & (!(enemyAircraft instanceof BossEnemy))) {
                subscribers.add(enemyAircraft);
            }
        }
        for (BaseBullet enemyBullet : enemyBullets) {
            if (!enemyBullet.notValid()) {
                subscribers.add(enemyBullet);
            }
        }
        for (AbstractSubscriber subscriber : subscribers) {
            //删除无效订阅者
            if (subscriber instanceof AbstractFlyingObject
                    && ((AbstractFlyingObject) subscriber).notValid()) {
                bombProp.removeSubscriber(subscriber);
            }
            //添加新的订阅者
            bombProp.addSubscriber(subscriber);
        }
    }

    /**
     * 随时间提升游戏难度
     */
    abstract void increaseDifficulty () ;

    /**
     * 产生boss机，如必要更改boss机血量
     */
    abstract EnemyFactory createBoss () ;

    /**
     * 钩子方法，决定游戏难度是否随时间提高
     * @return true则游戏难度需要随时间提高，反之false
     */
    boolean increaseDifficultyHook () {
        return true;
    }

    /**
     * 钩子方法，根据游戏难度决定是否产生boss机
     * @return true则要产生boss机，反之false
     */
    boolean creatBossHook () {
        return true;
    }

    /**
     * 后处理：
     * 1. 删除无效的子弹
     * 2. 删除无效的敌机
     * 3. 检查英雄机生存
     * 4. 删除无效的道具
     * <p>
     * 无效的原因可能是撞击或者飞出边界
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void postProcessAction() {
        enemyBullets.removeIf(AbstractFlyingObject::notValid);
        heroBullets.removeIf(AbstractFlyingObject::notValid);
        enemyAircrafts.removeIf(AbstractFlyingObject::notValid);
        props.removeIf(AbstractFlyingObject::notValid);
    }

    //***********************
    //      Paint 各部分
    //***********************

    /**
     * 重写paint方法
     * 通过重复调用paint方法，实现游戏动画
     */
    public void draw() {
        canvas = mSurfaceHolder.lockCanvas();
        if(mSurfaceHolder == null || canvas == null){
            return;
        }
        mPaint.setAntiAlias(true);

        // 绘制背景,图片滚动
        canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop - ImageManager.BACKGROUND_IMAGE.getHeight(), mPaint);
        canvas.drawBitmap(ImageManager.BACKGROUND_IMAGE, 0, this.backGroundTop, mPaint);
        this.backGroundTop += 1;
        if (this.backGroundTop == MainActivity.screenHeight) {
            this.backGroundTop = 0;
        }
        // 先绘制子弹、道具，后绘制飞机
        // 这样子弹、道具显示在飞机的下层
        paintImageWithPositionRevised(canvas, enemyBullets);
        paintImageWithPositionRevised(canvas, heroBullets);
        paintImageWithPositionRevised(canvas, props);

        paintImageWithPositionRevised(canvas, enemyAircrafts);
        heroAircraft.setLocation(x,y);
        canvas.drawBitmap(ImageManager.HERO_IMAGE,
                heroAircraft.getLocationX() - ImageManager.HERO_IMAGE.getWidth() / 2,
                heroAircraft.getLocationY() - ImageManager.HERO_IMAGE.getHeight() / 2, mPaint);

        //绘制得分和生命值
        paintScoreAndLife(canvas);
        paintCreditProp();

        //绘制图片要在这条语句之前哦！！！
        mSurfaceHolder.unlockCanvasAndPost(canvas);
    }

    /* 绘制积分商城的道具*/
    private void paintCreditProp () {
        canvas.drawBitmap(ImageManager.GOD_PROP_IMAGE,
                MainActivity.screenWidth - ImageManager.GOD_PROP_IMAGE.getWidth(),
                MainActivity.screenHeight - ImageManager.GOD_PROP_IMAGE.getHeight(), mPaint);
    }

    private void paintImageWithPositionRevised(Canvas canvas, List<? extends AbstractFlyingObject> objects) {
        if (objects.size() == 0) {
            return;
        }

        for (int i=0;i<objects.size();i++) {
            Bitmap image = objects.get(i).getImage();
            assert image != null : objects.getClass().getName() + " has no image! ";
            canvas.drawBitmap(image, objects.get(i).getLocationX() - image.getWidth() / 2,
                    objects.get(i).getLocationY() - image.getHeight() / 2, mPaint);
        }
    }

    private void paintScoreAndLife(Canvas canvas) {
        int x = 20;
        int y = 55;
        mPaint.setColor(Color.RED);
        mPaint.setFakeBoldText(true);
        mPaint.setTextSize(50);
        canvas.drawText("SCORE:" + this.score, x, y,mPaint);
        y = y + 60;
        canvas.drawText("LIFE:" + this.heroAircraft.getHp(), x, y,mPaint);
    }

    private void loading_img() {
        ImageManager.BACKGROUND_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
        ImageManager.HERO_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.hero);
        ImageManager.MOB_ENEMY_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.mob);
        ImageManager.ELITE_ENEMY_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.elite);
        ImageManager.BOSS_ENEMY_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.boss);
        ImageManager.HERO_BULLET_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_hero);
        ImageManager.ENEMY_BULLET_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.bullet_enemy);
        ImageManager.BLOOD_PROP_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.prop_blood);
        ImageManager.BOMB_PROP_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.prop_bomb);
        ImageManager.BULLET_PROP_IMAGE = BitmapFactory.decodeResource(getResources(), R.drawable.prop_bullet);
        ImageManager.GOD_PROP_IMAGE = BitmapFactory.decodeResource(getResources(),R.drawable.prop_god);

        ImageManager.CLASSNAME_IMAGE_MAP.put(HeroAircraft.class.getName(), ImageManager.HERO_IMAGE);
        ImageManager.CLASSNAME_IMAGE_MAP.put(MobEnemy.class.getName(), ImageManager.MOB_ENEMY_IMAGE);
        ImageManager.CLASSNAME_IMAGE_MAP.put(EliteEnemy.class.getName(), ImageManager.ELITE_ENEMY_IMAGE);
        ImageManager.CLASSNAME_IMAGE_MAP.put(BossEnemy.class.getName(), ImageManager.BOSS_ENEMY_IMAGE);
        ImageManager.CLASSNAME_IMAGE_MAP.put(HeroBullet.class.getName(), ImageManager.HERO_BULLET_IMAGE);
        ImageManager.CLASSNAME_IMAGE_MAP.put(EnemyBullet.class.getName(), ImageManager.ENEMY_BULLET_IMAGE);
        ImageManager.CLASSNAME_IMAGE_MAP.put(BloodProp.class.getName(), ImageManager.BLOOD_PROP_IMAGE);
        ImageManager.CLASSNAME_IMAGE_MAP.put(BombProp.class.getName(), ImageManager.BOMB_PROP_IMAGE);
        ImageManager.CLASSNAME_IMAGE_MAP.put(BulletProp.class.getName(), ImageManager.BULLET_PROP_IMAGE);
        ImageManager.CLASSNAME_IMAGE_MAP.put(GodProp.class.getName(), ImageManager.GOD_PROP_IMAGE);
    }


    public static int getScore() {
        return score;
    }

    public static void setScore(int score) {
        Game.score = score;
    }

}
