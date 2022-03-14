#include <pthread.h>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#include <time.h>
#include <stdlib.h>

struct {
  int* array;  // array
  int n;       // size of array
} data;

pthread_cond_t cv;
pthread_mutex_t mp;
int updated = 0;
int terminate = 0;

pthread_cond_t cv2;
pthread_mutex_t mp2;
int donePrinting = 0;

void* cal(void* a) {
  pthread_mutex_lock(&mp);

  int* arr = a;
  int i;
  printf("Passed in array: \n");
  for (i = 0; i < 5; i++)
    printf("%d ", arr[i]);
  printf("\n");

  while (1) {
    while (updated == 0 && terminate == 0)
      pthread_cond_wait(&cv, &mp);

    if (terminate)
      break;

    printf("Updated: ");
    for (int i = 0; i < data.n; i++) {
      printf("%d ", data.array[i]);
      fflush(stdout);
    }
    printf("\n");
    fflush(stdout);
    updated = 0;

    pthread_mutex_lock(&mp2);
    donePrinting = 1;
    pthread_cond_signal(&cv2);  // Signal to main that we are done printing
    pthread_mutex_unlock(&mp2);
  }
  pthread_mutex_unlock(&mp);

  pthread_exit(NULL);
}

void changeArray(int n) {
  pthread_mutex_lock(&mp);
  free(data.array);
  data.array = (int*)malloc(n * sizeof(int));
  data.n = n;
  int i;
  for (i = 0; i < n; i++) {
    data.array[i] = rand() % 10;
  }
  updated = 1;
  pthread_cond_signal(&cv);
  pthread_mutex_unlock(&mp);
}

int main(void) {
  pthread_t thread;
  void* status;
  int array[5] = {1, 2, 3, 4, 5};
  data.array = NULL;
  data.n = 0;
  srand(time(NULL));

  pthread_cond_init(&cv, NULL);
  pthread_cond_init(&cv2, NULL);
  pthread_mutex_init(&mp, NULL);
  pthread_mutex_init(&mp2, NULL);

  pthread_create(&thread, NULL, cal, (void*)&array);

  // """"""""""""
  // Change the array

  changeArray(5);

  // Wait for the second thread to finish printing
  pthread_mutex_lock(&mp2);
  while (!donePrinting)
    pthread_cond_wait(&cv2, &mp2);
  pthread_mutex_unlock(&mp2);

  // reset donePrinting
  pthread_mutex_lock(&mp2);
  donePrinting = 0;
  pthread_mutex_unlock(&mp2);

  // Change the array again
  changeArray(7);

  // Wait for the second thread to finish printing
  pthread_mutex_lock(&mp2);
  while (!donePrinting)
    pthread_cond_wait(&cv2, &mp2);
  pthread_mutex_unlock(&mp2);

  // reset donePrinting
  pthread_mutex_lock(&mp2);
  donePrinting = 0;
  pthread_mutex_unlock(&mp2);

  // Change the array a third time
  changeArray(10);

  // """"""""""""""""

  // Wait for the second thread to finish printing
  pthread_mutex_lock(&mp2);
  while (!donePrinting)
    pthread_cond_wait(&cv2, &mp2);
  pthread_mutex_unlock(&mp2);

  // send terminate request
  pthread_mutex_lock(&mp);
  terminate = 1;
  pthread_cond_signal(&cv);
  pthread_mutex_unlock(&mp);

  int rc = pthread_join(thread, &status);
  if (rc) {
    printf("ERROR; return code from pthread_join() is %d\n", rc);
    exit(-1);
  }

  pthread_exit(NULL);
}