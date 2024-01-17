#include <stdio.h>

void functionTemperature(int j);

int temperature_value;
int altitude_value;
int total_correction;
int altitudes[13]={100, 200,300,400, 500,600,700, 800,900,1000, 1500,2000,3000};

int main()
{
    char ask[2];
    do {
        temperature_value = 0;
        altitude_value = 0;
        total_correction = 0;

        printf("\nEnter temperature value (Celsius) in precision of 10. Range [0, -50]: ");
        scanf_s("%d", &temperature_value);

        if (temperature_value == 0){
            functionTemperature(0);
        }
        else if (temperature_value == -10){
            functionTemperature(1);
        }
        else if (temperature_value == -20){
            functionTemperature(2);
        }
        else if (temperature_value == -30){
            functionTemperature(3);
        }
        else if (temperature_value == -40){
            functionTemperature(4);
        }
        else if (temperature_value == -50){
            functionTemperature(5);
        }
        else {
            printf("Temperature not in range! Enter in precision of 10, numerical value only\n");
        }
        printf("\nContinue? yes (y) or no (n) ");
        scanf_s("%s", ask);
    } while(ask[0] == 'y');
    return 0;
}

void functionTemperature(int j)
{
    int table_of_corrections[6][13]=
    {
        {10, 20,20,30,  30,40,40,    50,50,60,     90,120,170},     // 0 -- 0 C
        {10, 20,30,40,  50,60,70,    80,90,100,   150,200,290},
        {20, 30,50,60,  70,90,100,   120,130,140, 210,280,420},
        {30, 40,60,80,  100,120,140, 150,170,190, 280,380,570},
        {30, 50,80,100, 120,150,170, 190,220,240, 360,480,720},
        {40, 60,90,120, 150,180,210, 240,270,300, 450,590,890}      // 5 -- -50 C
    };

    printf("Enter altitude value (feet) rounded to the nearest hundred: ");
    scanf_s("%d", &altitude_value);
    int n = 12;
    int alt_endsum = altitude_value;
    do{
        if (alt_endsum >= altitudes[n]) {
            total_correction += table_of_corrections[j][n];
            alt_endsum -= altitudes[n];
        }
        else {
            while (alt_endsum < altitudes[n]){
                n--;
            }
            total_correction += table_of_corrections[j][n];
            alt_endsum -= altitudes[n];
        }
    } while(alt_endsum >0);
    printf("\n|| Total correction: %d ft ||\n\n *** Corrected altitude: %d ft ***\n", total_correction, altitude_value+total_correction);
}
