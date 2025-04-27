import mpi.*;

public class Arraysum {
    public static void main(String[] args) throws MPIException {
        // Initialize MPI environment
        MPI.Init(args);

        // Get rank and size of the communicator
        int rank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size();

        // Define array size (N)
        int N = 16; // Example: 16 elements (must be divisible by size for simplicity)
        
        // Initialize array on all processes to avoid null in Scatter
        int[] array = new int[N];
        if (rank == 0) {
            // Populate array with values (e.g., 1, 2, ..., N) on root
            for (int i = 0; i < N; i++) {
                array[i] = i + 1; // Values 1 to N
            }
            System.out.println("Original array: ");
            for (int i = 0; i < N; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println();
        }

        // Calculate elements per processor (N/n)
        int elementsPerProc = N / size;
        int[] subArray = new int[elementsPerProc];

        // Scatter array to all processors
        MPI.COMM_WORLD.Scatter(array, 0, elementsPerProc, MPI.INT,
                               subArray, 0, elementsPerProc, MPI.INT, 0);

        // Compute local sum
        int localSum = 0;
        for (int i = 0; i < elementsPerProc; i++) {
            localSum += subArray[i];
        }

        // Print intermediate sum from each processor
        System.out.println("Process " + rank + " intermediate sum: " + localSum);

        // Prepare local sum as a single-element array for Gather
        int[] localSumArray = new int[1];
        localSumArray[0] = localSum;

        // Gather local sums to root
        int[] gatheredSums = new int[size];
        MPI.COMM_WORLD.Gather(localSumArray, 0, 1, MPI.INT,
                              gatheredSums, 0, 1, MPI.INT, 0);

        // Compute and display final sum on root
        if (rank == 0) {
            int finalSum = 0;
            for (int i = 0; i < size; i++) {
                finalSum += gatheredSums[i];
            }
            System.out.println("Final sum: " + finalSum);
        }

        // Finalize MPI environment
        MPI.Finalize();
    }
}


*****************HOw to run*********************

  Compile: javac -cp $MPJ_HOME/lib/mpj.jar Asign2.java 
(mpj.jar is inside lib folder in the downloaded MPJ Express) 
Execute: $MPJ_HOME/bin/mpjrun.sh -np 4 Asign2 
