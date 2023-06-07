package sequential;

import java.util.Arrays;
import java.util.Random;

public class Qlearning {
   private final double ALPHA = 0.1;
    private final double GAMMA = 0.9;
    private final int MAX_EPOCH = 2000;
    private final int GRID_SIZE = 3;
    private final int ACTION_SIZE = 4;
    private int[][] grid;
    private double[][] qTable = new double[GRID_SIZE*GRID_SIZE][ACTION_SIZE];
    private int[][] actions;
    private int stateI;
    private int stateJ;

    public Qlearning() {
        actions = new int[][]{
                {0,-1},    // gauche
                {0,1},     // droite
                {1,0},     // bas
                {-1,0},    // haut
            };

        grid = new int[][]{
                {1,0,0},
                {-1,0,0},
                {0,0,0},
        };
    }

    // initialiser vers l'etat initial
    public void resetState(){
        stateI = 2;
        stateJ = 0;
    }

    private int chooseAction(double eps){
        Random random = new Random();
        double bestQ=0;
        int act=0;
        if (random.nextDouble() < eps){
            // exploration
            act = random.nextInt(ACTION_SIZE);
        }else {
            // exploitation
            int st = stateI*GRID_SIZE + stateJ;

            for (int i=0; i<ACTION_SIZE;i++){
                if (qTable[st][i]>bestQ){
                    bestQ = qTable[st][i];
                    act = i;
                }
            }
        }

        return act;
    }

    public int executeAction(int act){
        stateI = Math.max(0, Math.min(actions[act][0] + stateI, 2));
        stateJ = Math.max(0, Math.min(actions[act][1] + stateJ, 2));
        return stateI*GRID_SIZE + stateJ;
    }

    private boolean finished(){
        return grid[stateI][stateJ] ==1;
    }

    private void showResult(){
        System.out.println("*** q table ***");
        for(double []line:qTable){
            System.out.printf("[");

            for (double qvalue:line){
                System.out.printf(qvalue+ ",");
            }
            System.out.println("]");
        }

        System.out.println("*** path ***");
        resetState();

        while (!finished()){
            int act = chooseAction(0);
            System.out.println("State " + (stateI*GRID_SIZE + stateJ) + " action : "+act);
            executeAction(act);
        }
        System.out.println("Final State " + (stateI*GRID_SIZE + stateJ));
    }
    public void runQlearning(){
        int it = 0;
        resetState();
        int currentState;
        int nextState;
        int act, act1;

        while (it<MAX_EPOCH){
            resetState();

            while (!finished()){
                currentState = stateI*GRID_SIZE + stateJ;
                act = chooseAction(0.4);
                nextState = executeAction(act);

                act1 = chooseAction(0);

                qTable[currentState][act] = qTable[currentState][act] + ALPHA * (grid[stateI][stateJ] + GAMMA*qTable[nextState][act1]-qTable[currentState][act]);
                it++;
            }
        }

        showResult();
    }
}
