package ArmyAnt;

/**
 * Created by Jason-Zhao-Jie on 2016/5/21.
 */
public class ArmyAnt {
    public static final double c_natrualBase = 2.7182818284590452353;    // 自然对数底
    public static final double c_pi = 3.1415926535897932384;            // 圆周率
    public static final double c_goldenScale = 0.6180339887498948482;    // 黄金分割比

    // Physics constants
    public static final int c_lightVacuum = 299792458;    // (m/s)	光速
    public static final float c_gravitation = 6.67310e-11f;    // (m^3/(kg*s^2))	引力常数
    public static final double c_planck = 6.6260687652e-34;    // (J*s)	普朗克常量
    public static final double c_elementaryCharge = 1.60217646263e-19;    // (C)	电子电荷量
    public static final double c_lightYear = 9.4607304725808e15;    // (m)	光年
    public static final double c_electronVolt = 1.60217646263e-19;    // (J)	电子伏
    public static final double c_avogadro = 6.0221422026e23;        // 阿伏伽德罗常量(常数), 摩尔质量系数

    // Binary units conversion
    public static final short c_2xK = 0b10000000000;
    public static final int c_2xM = c_2xK * c_2xK;
    public static final int c_2xG = c_2xM * c_2xK;
    public static final long c_2xT = c_2xG * c_2xK;
    public static final long c_2xP = c_2xT * c_2xK;
    public static final long c_2xE = c_2xP * c_2xK;

    public static void DebugLog(java.lang.String str) {
        System.out.println(str);
    }

    public static void DebugError(java.lang.String str) {
        System.err.println(str);
    }
}