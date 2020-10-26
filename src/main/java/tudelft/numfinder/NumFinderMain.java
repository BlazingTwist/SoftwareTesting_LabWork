package tudelft.numfinder;

public class NumFinderMain {

    public static void main (String[] args) {
        NumFinder nf = new NumFinder();

        // this works
        //nf.find(new int[] {4, 25, 7, 9});
        nf.find(new int[] {4, 3, 2, 1});



        System.out.println("LArgest: "+nf.getLargest());
        System.out.println("Smallest: "+nf.getSmallest());
    }
}
