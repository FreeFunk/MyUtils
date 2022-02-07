/**
 * @Author:Qiutianzhu
 * @Date 2021-12-31 10:48
 * @Description: JAVA算法50题
 */
public class AlgorithmUtils {

    /***
     * @Author:Qiutianzhu
     * @Description: 主方法调用
     * @Date: 2021/12/31 10:57
     **/
    public static void main(String[] args){
        int f1=1,f2=1,f;
        int M=30;
        for(int i=3;i<M;i++) {
            f = f2;
            f2 = f1 + f2;
            f1 = f;
            System.out.println(f2);
        }
    }

    /**
     * @Author:Qiutianzhu
     * @Description: 兔子问题 (斐波那契数列求值)
     * 古典问题：有一对兔子，从出生后第3个月起每个月都生一对兔子，小兔子长到第三个月后每个月又生一对兔子，
     * 假如兔子都不死，问每个月的兔子总数为多少？
     * @Date: 2021/12/31 10:50
     **/
    public static void rabbitAlgorithmMethod(){
        //1. 1 1  month 2 30*2 60
        //1.初始兔子个数，准备统计到第几个月
        long rabbitNum = 1L;
        long motherRabbitNum = 1L;
        int countMonthNum = 10;
        //2.提供变量作为中间固值，作为输出值
        long flagRabbit;
        //3.依据月数循环
        for (int i = 3; i <= countMonthNum; i++){
            //一对兔子之和一对--（出生一对）

        }
    }




}
