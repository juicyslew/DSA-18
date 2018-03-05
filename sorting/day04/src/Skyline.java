import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Skyline {

    static class Point {
        int x, y;
        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Building {
        private int l, r, h;
        Building(int l, int r, int h) {
            this.l = l;
            this.r = r;
            this.h = h;
        }
    }

    // Given an array of buildings, return a list of points representing the skyline
    public static List<Point> skyline(Building[] B) {
        List<Building> Blist = Arrays.asList(B); // O(n)
        List<Point> Plist = recurseSkyline(Blist); // O(nLog(n))

        return Plist;
    }
    public static List<Point> recurseSkyline(List<Building> B) {
        if (B.size() == 1){
            Building b = B.get(0);
            List<Point> tmpPlist = new ArrayList<Point>();

            tmpPlist.add(new Point(b.l, b.h));
            tmpPlist.add(new Point(b.r, 0));
            return tmpPlist;
        }else{
            int halfway = (int)Math.floor(B.size()/2);
            List<Point> p1 = recurseSkyline(B.subList(0, halfway));
            List<Point> p2 = recurseSkyline(B.subList(halfway, B.size()));
            List<Point> pfinal = new ArrayList<Point>();
            int i = 0;
            int j = 0;
            //boolean lastj = false;
            //boolean lasti = false;

            //boolean first = true;


            //Merge
            while (i < p1.size() || j < p2.size()) {
                //boolean nowi = false;
                //boolean nowj = false;
                Point tmppoint;
                Point refpoint;
                boolean toggle = false;
                //check what state to be in
                if (j == p2.size()) {
                    toggle = true;
                }else if (i == p1.size()){
                    toggle = false;
                }else if (p1.get(i).x < p2.get(j).x){
                    toggle = true;
                }else{
                    toggle = false;
                }

                if (toggle) {
                    tmppoint = p1.get(i);
                    if (j == 0) {
                        refpoint = null;
                    }else{
                        refpoint = p2.get(j-1);
                    }
                    //nowi = true;
                    i++;
                }else{
                    //first = false;
                    tmppoint = p2.get(j);
                    if (i == 0) {
                        refpoint = null;
                    }else {
                        refpoint = p1.get(i-1);
                    }
                    //nowj = true;
                    j++;
                }

                if (refpoint == null){
                    pfinal.add(tmppoint);
                }else{
                    /*if (nowi == lasti || nowj == lastj)
                    if(tmppoint.y == refpoint.y){
                        nowi = true;
                        nowj = true;
                    }else */
                    if (tmppoint.y > refpoint.y){
                        if (tmppoint.x == refpoint.x && pfinal.get(pfinal.size()-1).x == tmppoint.x){

                            pfinal.remove(pfinal.size()-1);
                        }
                        if (pfinal.size() > 0 && tmppoint.y == pfinal.get(pfinal.size()-1).y){

                        }else {
                            pfinal.add(tmppoint);
                        }
                    }else{
                        System.out.println("tmppoint: " + tmppoint.x + " | " + tmppoint.y);
                        System.out.println("refpoint: " + refpoint.x + " | " + refpoint.y);
                        System.out.println("pfinalpoint: " + pfinal.get(pfinal.size()-1).x + " | " + pfinal.get(pfinal.size()-1).y);
                        if (pfinal.get(pfinal.size()-1).x == refpoint.x && pfinal.get(pfinal.size()-1).y == refpoint.y){
                            //do nothing
                        }else {
                            if (pfinal.get(pfinal.size()-1).y > refpoint.y) {
                                if (pfinal.get(pfinal.size()-1).x != tmppoint.x) {
                                    pfinal.add(new Point(tmppoint.x, refpoint.y));
                                }
                            }else if (pfinal.get(pfinal.size()-1).y == refpoint.y){
                                System.out.println(pfinal.get(pfinal.size()-1).y + " | " + refpoint.y);
                                //do nothing
                                //System.out.println("wowowow1");
                            } else {
                                //System.out.println("wiwiwiw2");
                            }

                            /*if (pfinal.get(pfinal.size()-1).x != tmppoint.x){
                                if (pfinal.get(pfinal.size()-1).y != refpoint.y){
                                    pfinal.add(new Point(tmppoint.x, refpoint.y));
                                }
                            }else{
                                if (pfinal.get(pfinal.size()-1).y < refpoint.y){
                                    pfinal.remove(pfinal.size()- 1);
                                    pfinal.add(new Point(tmppoint.x, refpoint.y));
                                }
                            }*/
                        }
                        /*if (pfinal.get(pfinal.size()-1).x != tmppoint.x){
                            if (pfinal.get(pfinal.size()-1).y != refpoint.y){
                                pfinal.add(new Point(tmppoint.x, refpoint.y));
                            }
                        }else{
                            if (pfinal.get(pfinal.size()-1).y < tmppoint.y){
                                pfinal.remove(pfinal.size()- 1);
                                pfinal.add(new Point(tmppoint.x, refpoint.y));
                            }
                        }*/
                    }
                }
                //lasti = nowi;
                //lastj = nowj;
                System.out.print("Array:");
                for (int k = 0; k < pfinal.size(); k++) {
                    System.out.print("   " + pfinal.get(k).x + " | " + pfinal.get(k).y);
                }
                System.out.println("");
            }
            return pfinal;
        }
    }
}
