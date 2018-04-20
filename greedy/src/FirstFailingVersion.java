public class FirstFailingVersion {

    public static long firstBadVersion(long n, IsFailingVersion isBadVersion) {
        long lo = n;
        while(isBadVersion.isFailingVersion(lo)){
            lo /= 2;
        }
        long hi = n;
        while (!isBadVersion.isFailingVersion(hi)){
            hi *= 2;
        }

        //boolean first = false;
        while ((hi - lo) > 1) {
            long newval = (hi+lo)/2;
            System.out.println(hi + " | " + lo );
            if(isBadVersion.isFailingVersion(newval)){
                hi = newval;
            }else{
                lo = newval;
            }
        }

        return hi;
    }
}
