package sma;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.SequentialBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.Random;

public class QLearningAgent extends Agent {
    private int[][] grid;
    private double[][] qTable = new double[QUtils.GRID_SIZE*QUtils.GRID_SIZE][QUtils.ACTION_SIZE];
    private int[][] actions;
    private int stateI;
    private int stateJ;

    String path = "";

    @Override
    protected void setup() {
        SequentialBehaviour sequentialBehaviour = new SequentialBehaviour();

        sequentialBehaviour.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                initialisation();
                int it = 0;
                int currentState;
                int nextState;
                int act, act1;

                while (it<QUtils.MAX_EPOCH){
                    resetState();

                    while (!finished()){
                        //System.out.println("Bonsoir");
                        currentState = stateI*QUtils.GRID_SIZE + stateJ;
                        act = chooseAction(0.4);
                        nextState = executeAction(act);

                        act1 = chooseAction(0);

                        qTable[currentState][act] = qTable[currentState][act] + QUtils.ALPHA * (grid[stateI][stateJ] + QUtils.GAMMA*qTable[nextState][act1]-qTable[currentState][act]);
                        it++;
                    }
                }

                showResult();
            }
        });

        sequentialBehaviour.addSubBehaviour(new OneShotBehaviour() {
            @Override
            public void action() {
                ACLMessage aclMessage = new ACLMessage(ACLMessage.QUERY_IF);
                aclMessage.setContent(path);
                aclMessage.addReceiver(new AID("main", AID.ISLOCALNAME));
                send(aclMessage);
            }
        });

        addBehaviour(sequentialBehaviour);

    }

    public void initialisation(){
        actions = new int[][]{
                {0,-1},    // gauche
                {0,1},     // droite
                {1,0},     // bas
                {-1,0},    // haut
        };

        grid = new int[][]{
                {-1,0,1,0},
                {-1,0,0,-1},
                {0,0,-1,0},
                {0,0,0,0},
        };
    }

    // initialiser vers l'etat initial
    public void resetState(){
        stateI = 3;
        stateJ = 3;
    }

    private int chooseAction(double eps){
        Random random = new Random();
        double bestQ=0;
        int act=0;

        if (random.nextDouble() < eps){
            // exploration
            act = random.nextInt(QUtils.ACTION_SIZE);
        }else {
            // exploitation
            int st = stateI*QUtils.GRID_SIZE + stateJ;

            for (int i=0; i<QUtils.ACTION_SIZE;i++){
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
        return stateI*QUtils.GRID_SIZE + stateJ;
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

        int cpt=0;

        while (!finished()){
            int act = chooseAction(0);
            System.out.println("State " + (stateI*QUtils.GRID_SIZE + stateJ) + " action : "+act);

            path += "State " + (stateI*QUtils.GRID_SIZE + stateJ) + " action : "+act + "\n";
            executeAction(act);
            cpt++;
        }
        System.out.println("Final State " + (stateI*QUtils.GRID_SIZE + stateJ));
        path += "Final State " + (stateI*QUtils.GRID_SIZE + stateJ) + "\n" + "=>" + cpt;

    }

}
