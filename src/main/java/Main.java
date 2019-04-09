import java.io.File;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        // Loads the preferred file from the resources folder from the project
        String fileName = "ch130.tsp";
        ClassLoader classLoader = new Main().getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        List<City> listCities;

        // Reads the city info from the loaded file
        listCities = TSPParser.Parse(file);

        //This is the main tour passed to the alorithms
        City[] tour = new City[listCities.size()];

        TSPAlgorithm tsp = new TSPAlgorithm(listCities);

        System.out.println("----------NearestNeighbor----------");
        NearestNeighbor nn = new NearestNeighbor(tsp);
        nn.compute(tour);
        System.out.println("Lunghezza " + tsp.tourLength(tour));
        System.out.println("Errore " + tsp.printError(tour, 6110));

        System.out.println("----------TwoOpt----------");
        TwoOpt to = new TwoOpt(tsp);
        to.compute(tour);
        System.out.println("Lunghezza " + tsp.tourLength(tour));
        System.out.println("Errore " + tsp.printError(tour, 6110));
    }

    // Static method that print any matrix to terminal
    public static void printMatrix(int[][] matrix) {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix[x].length; y++) {
                System.out.print(matrix[x][y] + "\t");
            }
            System.out.println();
        }
    }
}
