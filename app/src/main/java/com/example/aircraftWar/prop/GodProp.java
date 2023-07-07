package com.example.aircraftWar.prop;

public class GodProp extends AbstractProp{

    //用户拥有的道具数量
    public static int godPropNumber = 0;
    //Context context;

    private static boolean isEffect = false;

    public GodProp () {
        super();
        //this.context = context;
    }

    @Override
    /**
     * 免除伤害道具效果：在一段时间内受到子弹攻击不减少生命值
     */
    public void effect(){
        if (godPropNumber > 0 ) {
            isEffect = true;
            Runnable r = ()-> {
                //todo:减
                System.out.println("GOD_PROP active!");
                try {
                    Thread.sleep(10000);
                    isEffect = false;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
            new Thread(r).start();
        }
    }

    public static boolean isGodEffect() {
        return isEffect;
    }

    /**
    private void writeFile () {
        FileOutputStream fileOutputStream = null;
        try {
            String text = String.valueOf(number);
            fileOutputStream = context.openFileOutput("godNumber.txt", MODE_PRIVATE);
            fileOutputStream.write(text.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void readFile () {
        FileInputStream fileInputStream = null;
        Reader reader = null;
        BufferedReader bufferedReader = null;
        try {
            fileInputStream = context.openFileInput("godNumber.txt");
            reader = new InputStreamReader(fileInputStream);// 字符流
            bufferedReader = new BufferedReader(reader); //缓冲流
            StringBuilder result = new StringBuilder();
            String temp;
            while ((temp = bufferedReader.readLine()) != null) {
                number = Integer.parseInt(temp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
     */
}