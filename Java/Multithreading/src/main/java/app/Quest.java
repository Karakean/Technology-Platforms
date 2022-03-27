package app;

public class Quest {
    private int number;

    public Quest(int number) {
        this.number = number;
    }

    public boolean isPrime(){
        if (number <= 1)
            return false;
        for(int i = 2; i*i <= number; i++){
            System.out.println(Thread.currentThread().getId());
            if(number%i == 0)
                return false;
        }
        return true;
    }
}
