package ArmyAnt.Utils;

/**
 * Created by admin on 2016/5/21.
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
        return Integer.toString(bigVer) + '.' + Integer.toString(smallVer) + '.' + Integer.toString(patchVer) + '.' + Integer.toString(testVer);
    }

    public int GetVer() {
        return ((bigVer * 256 + smallVer) * 256 + patchVer) * 256 + testVer;
    }

    public char GetBigVer() {
        return bigVer;
    }

    public char GetSmallVer() {
        return smallVer;
    }

    public char GetPatchVer() {
        return patchVer;
    }

    public char GetTestVer() {
        return testVer;
    }

    private char bigVer;
    private char smallVer;
    private char patchVer;
    private char testVer;
}
