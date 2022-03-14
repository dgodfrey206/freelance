#include <stdio.h>

int main() {
    int i, k, j, d;
    int binary[8] = {};
    for (i = 1; i < 256; i++) {
        d = i;
        k = 0;
        while (d != 0) {
            binary[k++] = d % 2;
            d /= 2;
        }
        for (j = 7; j >= 0; j--) {
            printf("%d ", binary[j]);
        }
        printf("\n");
    }
}