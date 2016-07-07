package ArmyAnt.Utils;

/**
 * Created by Jason-Zhao-Jie on 2016/5/21.
 */
public class Version {
    public Version(int ver) {
        long res = 0;
        if (ver < 0)
            res = (long) (Integer.MAX_VALUE) * 2 + 1 + ver;
        testVer = (char) (res % 256);
        ver /= 256;
        patchVer = (char) (res % 256);
        ver /= 256;
        smallVer = (char) (res % 256);
        ver /= 256;
        bigVer = (char) (res % 256);
    }

    public Version(char bigVer, char smallVer, char patchVer, char testVer) {
        this.bigVer = bigVer;
        this.smallVer = smallVer;
        this.patchVer = patchVer;
        this.testVer = testVer;
    }

    public java.lang.String GetVerStr() {
        return "" + bigVer + '.' + smallVer + '.' + patchVer + '.' + testVer;
    }

    public int GetVersionNum() {
        return ((bigVer * 256 + smallVer) * 256 + patchVer) * 256 + testVer;
    }

    public char bigVer;
    public char smallVer;
    public char patchVer;
    public char testVer;
}
