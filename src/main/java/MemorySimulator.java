import java.util.Random;

public class MemorySimulator {

    public static void main(String[] args) {
        int[] memoryArray = new int[100];
        userMemoryAllocation(memoryArray);
    }

    public static void userMemoryAllocation(int[] memoryArray) {
        Random rand = new Random();
        int currentAddress = 2000;
        int pagesOccupied = 0;
        int processId = 1;

        System.out.printf("%-15s %-25s %-25s %-15s%n",
                "Process Id", "Starting Memory Address", "Size of the Process MB", "Unused Space MB");

        while (pagesOccupied < 100) {
            int randomNum = rand.nextInt(30) + 1;
            int processSizeMB = randomNum * 80;

            double exactPages = (double) processSizeMB / 160;
            int pagesRequired = (int) Math.ceil(exactPages);

            if (pagesOccupied + pagesRequired > 100) {
                pagesRequired = 100 - pagesOccupied;
            }

            int totalAllocatedMB = pagesRequired * 160;
            int unusedSpaceMB = totalAllocatedMB - processSizeMB;

            for (int i = 0; i < pagesRequired; i++) {
                memoryArray[pagesOccupied + i] = processId;
            }

            System.out.printf("%-15d %-25d %-25d %-15d%n",
                    processId, currentAddress, processSizeMB, Math.max(0, unusedSpaceMB));

            currentAddress += totalAllocatedMB;
            pagesOccupied += pagesRequired;
            processId++;
        }
    }
}
