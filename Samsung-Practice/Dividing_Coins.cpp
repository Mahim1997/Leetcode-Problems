// ConsoleApplication2.cpp : This file contains the 'main' function. Program execution begins and ends there.
//

#define MAX_NUM_COINS 100


#include <iostream>
#include <fstream>
#include <string>

using namespace std;

void print_array(int arr[], int len) {
    for (int i = 0; i < len; i++) {
        cout << arr[i] << ", ";
    }
    cout << endl;
}

void selectionSort(int a[], int n) {
    int i, j, min, temp;
    for (i = 0; i < n - 1; i++) {
        min = i;
        for (j = i + 1; j < n; j++)
            if (a[j] < a[min])
                min = j;
        temp = a[i];
        a[i] = a[min];
        a[min] = temp;
    }
}

int getBestDifference(int arr[], int size_arr) {
    // Assume sorted ?
    int right = 0;
    int left = 0;

    selectionSort(arr, size_arr + 1);

    bool firstTry = true;
    for (int i = size_arr; i >= 0; i--) {
        int tempRight = right + arr[i];
        int tempLeft = left + arr[i];
        
        // cout << "DEBUG, i = " << i << ", arr[i] = " << arr[i] << endl;

        if (firstTry == true) {
            right += arr[i];
            firstTry = false;
            continue;
        }

        if (right > left) {
            left += arr[i];
        }
        else {
            right += arr[i];
        }
    }
    //cout << "DEBUG printing array: " << endl;
    //print_array(arr, size_arr + 1);
    //cout << "DEBUG, left = " << left << " , right = " << right << endl;
    int diff = right - left;
    return (diff > 0) ? diff : -diff;
}

int main()
{
    //ifstream fin("Input.txt");

    int test_cases;
    cin >> test_cases;
    
    int coins[MAX_NUM_COINS];
    int num_coins;

    while (test_cases--) {
        cin >> num_coins;
        for (int i = 0; i < num_coins; i++) {
            cin >> coins[i];
        }

        // print_array(coins, num_coins);
        int answer = getBestDifference(coins, num_coins - 1);

        cout << answer << endl;
    }

    //fin.close();
}
