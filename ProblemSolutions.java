
/******************************************************************
 * Julian Solis / 001 *
 *   This java file contains the problem solutions for the methods selectionSort,
 *   mergeSortDivisibleByKFirst, asteroidsDestroyed, and numRescueCanoes methods.
 *
 ********************************************************************/

import java.util.Arrays;

public class ProblemSolutions {

    /**
     * Method SelectionSort
     *
     * This method performs a selection sort. This file will be spot checked,
     * so ENSURE you are performing a Selection Sort!
     *
     * This is an in-place sorting operation that has two function signatures. This
     * allows the second parameter to be optional, and if not provided, defaults to
     * an
     * ascending sort. If the second parameter is provided and is false, a
     * descending
     * sort is performed.
     *
     * @param values    - int[] array to be sorted.
     * @param ascending - if true,method performs an ascending sort, else
     *                  descending.
     *                  There are two method signatures allowing this parameter
     *                  to not be passed and defaulting to 'true (or ascending
     *                  sort).
     */

    public void selectionSort(int[] values) {
        selectionSort(values, true);
    }

    public static void selectionSort(int[] values, boolean ascending) {
        int length = values.length;

        // Create an if-else statement as follows:
        if (ascending) {// if the user sets ascending to true, we perform an ascending selection sort
            for (int i = 0; i < length - 1; i++) {// Traverse the entirety of values
                int min = values[i];// the smallest value we've seen is at i
                int indexOfMin = i;// the smallest index we've seen
                for (int j = i + 1; j < length; j++) {// compare every index in the array for the smallest value, once
                                                      // we find the smallest value, swap the elements
                    if (values[j] < min) {// identify the smallest element in the array
                        min = values[j];// that element becomes the min
                        indexOfMin = j;// track the position of the min we found for swapping
                    }
                }
                swap(values, i, indexOfMin);// Create method for swapping, send parameters: array, position at which the
                                            // min element will be placed(i) and the indexOfMin for swapping of
                                            // positions between those elements
            }
        } else {// ascending is false, thus, the user wants the selection sortin descending
                // order

            for (int i = 0; i < length - 1; i++) {// Traverse the entirety of values
                int max = values[i];// the largest value we've seen is at i
                int indexOfMax = i;// the largest index we've seen
                for (int j = i + 1; j < length; j++) {// compare every index in the array for the largest value, once we
                                                      // find the largest value, swap the elements
                    if (values[j] > max) {// identify the largest element in the array
                        max = values[j];// that element becomes the max
                        indexOfMax = j;// track the position of the max we found for swapping
                    }
                }
                swap(values, i, indexOfMax);// Create method for swapping, send parameters: array, position at which the
                                            // max element will be placed(i) and the indexOfMax for swapping of
                                            // positions between those elements
            }

        }
    }

    private static void swap(int[] elements, int a, int b) {// method for swapping elements
        int temp = elements[a];// place element at index a into temporary variable
        elements[a] = elements[b];// place element at index b into index a
        elements[b] = temp;// Place the element that was previously at position a into the index that was
                           // previously held by element b
    }

    // End class selectionSort

    /**
     * Method mergeSortDivisibleByKFirst
     *
     * This method will perform a merge sort algorithm. However, all numbers
     * that are divisible by the argument 'k', are returned first in the sorted
     * list. Example:
     * values = { 10, 3, 25, 8, 6 }, k = 5
     * Sorted result should be --> { 10, 25, 3, 6, 8 }
     *
     * values = { 30, 45, 22, 9, 18, 39, 6, 12 }, k = 6
     * Sorted result should be --> { 30, 18, 6, 12, 9, 22, 39, 45 }
     *
     * As shown above, this is a normal merge sort operation, except for the numbers
     * divisible by 'k' are first in the sequence.
     *
     * @param values - input array to sort per definition above
     * @param k      - value k, such that all numbers divisible by this value are
     *               first
     */

    public void mergeSortDivisibleByKFirst(int[] values, int k) {

        // Protect against bad input values
        if (k == 0)
            return;
        if (values.length <= 1)
            return;

        mergeSortDivisibleByKFirst(values, k, 0, values.length - 1);
    }

    private void mergeSortDivisibleByKFirst(int[] values, int k, int left, int right) {

        if (left >= right)
            return;

        int mid = left + (right - left) / 2;
        mergeSortDivisibleByKFirst(values, k, left, mid);
        mergeSortDivisibleByKFirst(values, k, mid + 1, right);
        mergeDivisbleByKFirst(values, k, left, mid, right);
    }

    /*
     * The merging portion of the merge sort, divisible by k first
     */
    private void mergeDivisbleByKFirst(int arr[], int k, int left, int mid, int right) {
        // YOUR CODE GOES HERE, THIS METHOD IS NO MORE THAN THE STANDARD MERGE PORTION
        // OF A MERGESORT, EXCEPT THE NUMBERS DIVISIBLE BY K MUST GO FIRST WITHIN THE
        // SEQUENCE PER THE DISCUSSION IN THE PROLOGUE ABOVE.
        //
        // NOTE: YOU CAN PROGRAM THIS WITH A SPACE COMPLEXITY OF O(1) OR O(N LOG N).
        // AGAIN, THIS IS REFERRING TO SPACE COMPLEXITY. O(1) IS IN-PLACE, O(N LOG N)
        // ALLOCATES AUXILIARY DATA STRUCTURES (TEMPORARY ARRAYS). IT WILL BE EASIER
        // TO CODE WITH A SPACE COMPLEXITY OF O(N LOG N), WHICH IS FINE FOR PURPOSES
        // OF THIS PROGRAMMING EXERCISES.
        // Calculate the sizes of the two halves
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        // Create temporary arrays for the two halves
        int[] leftHalf = new int[leftSize];
        int[] rightHalf = new int[rightSize];

        // Copy elements into the leftHalf array
        for (int i = 0; i < leftSize; i++) {
            leftHalf[i] = arr[left + i];
        }

        // Copy elements into the rightHalf array
        for (int i = 0; i < rightSize; i++) {
            rightHalf[i] = arr[mid + 1 + i];
        }

        // Initialize pointers for merging
        int i = 0, j = 0, l = left;

        // First, merge elements divisible by k while maintaining their original order
        while (i < leftSize || j < rightSize) {
            // Check if the current elements in the left or right halves are divisible by k
            boolean leftDivisibleByK = i < leftSize && leftHalf[i] % k == 0;
            boolean rightDivisibleByK = j < rightSize && rightHalf[j] % k == 0;

            if (leftDivisibleByK && !rightDivisibleByK) {
                // Prioritize leftHalf if divisible by k
                arr[l++] = leftHalf[i++];
            } else if (rightDivisibleByK && !leftDivisibleByK) {
                // Prioritize rightHalf if divisible by k
                arr[l++] = rightHalf[j++];
            } else {
                break; // Stop prioritizing divisible numbers
            }
        }

        // Then merge the remaining elements (normal merge sort for non-divisible)
        while (i < leftSize && j < rightSize) {
            if (leftHalf[i] <= rightHalf[j]) {
                arr[l++] = leftHalf[i++];
            } else {
                arr[l++] = rightHalf[j++];
            }
        }

        // Copy any remaining elements from leftHalf
        while (i < leftSize) {
            arr[l++] = leftHalf[i++];
        }

        // Copy any remaining elements from rightHalf
        while (j < rightSize) {
            arr[l++] = rightHalf[j++];
        }
    }

    /**
     * Method asteroidsDestroyed
     *
     * You are given an integer 'mass', which represents the original mass of a
     * planet.
     * You are further given an integer array 'asteroids', where asteroids[i] is the
     * mass
     * of the ith asteroid.
     *
     * You can arrange for the planet to collide with the asteroids in any arbitrary
     * order.
     * If the mass of the planet is greater than or equal to the mass of the
     * asteroid, the
     * asteroid is destroyed and the planet gains the mass of the asteroid.
     * Otherwise, the
     * planet is destroyed.
     *
     * Return true if possible for all asteroids to be destroyed. Otherwise, return
     * false.
     *
     * Example 1:
     * Input: mass = 10, asteroids = [3,9,19,5,21]
     * Output: true
     *
     * Explanation: One way to order the asteroids is [9,19,5,3,21]:
     * - The planet collides with the asteroid with a mass of 9. New planet mass: 10
     * + 9 = 19
     * - The planet collides with the asteroid with a mass of 19. New planet mass:
     * 19 + 19 = 38
     * - The planet collides with the asteroid with a mass of 5. New planet mass: 38
     * + 5 = 43
     * - The planet collides with the asteroid with a mass of 3. New planet mass: 43
     * + 3 = 46
     * - The planet collides with the asteroid with a mass of 21. New planet mass:
     * 46 + 21 = 67
     * All asteroids are destroyed.
     *
     * Example 2:
     * Input: mass = 5, asteroids = [4,9,23,4]
     * Output: false
     *
     * Explanation:
     * The planet cannot ever gain enough mass to destroy the asteroid with a mass
     * of 23.
     * After the planet destroys the other asteroids, it will have a mass of 5 + 4 +
     * 9 + 4 = 22.
     * This is less than 23, so a collision would not destroy the last asteroid.
     *
     * Constraints:
     * 1 <= mass <= 105
     * 1 <= asteroids.length <= 105
     * 1 <= asteroids[i] <= 105
     *
     * @param mass      - integer value representing the mass of the planet
     * @param asteroids - integer array of the mass of asteroids
     * @return - return true if all asteroids destroyed, else false.
     */

    public static boolean asteroidsDestroyed(int mass, int[] asteroids) {
        // No asteroids to collide with
        if (asteroids.length < 1) {
            return true;
        }
        // YOUR CODE GOES HERE, CONSIDER USING ARRAYS.SORT()
        Arrays.sort(asteroids);
        // for each asteroid, check if the planet's current mass is greater than or
        // equal to the asteroid's mass
        for (int asteroid : asteroids) {
            if (mass >= asteroid) {
                mass += asteroid; // "Consume" the asteroid
            } else {
                return false; // The planet can't handle this asteroid
            }
        }
        return true; // All asteroids were destroyed

    }

    /**
     * Method numRescueSleds
     *
     * You are given an array people where people[i] is the weight of the ith
     * person,
     * and an infinite number of rescue sleds where each sled can carry a maximum
     * weight
     * of limit. Each sled carries at most two people at the same time, provided the
     * sum of the weight of those people is at most limit. Return the minimum number
     * of rescue sleds to carry every given person.
     *
     * Example 1:
     * Input: people = [1,2], limit = 3
     * Output: 1
     * Explanation: 1 sled (1, 2)
     *
     * Example 2:
     * Input: people = [3,2,2,1], limit = 3
     * Output: 3
     * Explanation: 3 sleds (1, 2), (2) and (3)
     *
     * Example 3:
     * Input: people = [3,5,3,4], limit = 5
     * Output: 4
     * Explanation: 4 sleds (3), (3), (4), (5)
     *
     * @param people - an array of weights for people that need to go in a sled
     * @param limit  - the weight limit per sled
     * @return - the minimum number of rescue sleds required to hold all people
     */

    public static int numRescueSleds(int[] people, int limit) {

        // Step 1: Sort the array of people's weights in ascending order.
        Arrays.sort(people);

        // Initialize a counter to keep track of the number of sleds used.
        int sledCounter = 0;

        // Use two pointers: one starting at the lightest person (left)
        // and the other at the heaviest person (right).
        int left = 0;
        int right = people.length - 1;

        // Continue until all people are accounted for.
        while (left <= right) {
            // Check if the lightest and heaviest person can share a sled.
            if (people[left] + people[right] <= limit) {
                // If they can, move the left pointer to the next person.
                left++;
            }

            // Move the right pointer to the next heaviest person
            // (this person always gets a sled, either alone or shared).
            right--;

            // Increment the sled counter for this pairing.
            sledCounter++;
        }

        // Return the total number of sleds needed.
        return sledCounter;
    }

} // End Class ProblemSolutions
