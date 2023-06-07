package sma;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class MainAgent extends Agent {

    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receiver = receive();

                if (receiver!=null){
                    System.out.println("Agent : " +receiver.getSender().getLocalName() + "\n");
                    System.out.println(receiver.getContent());
                }else block();
            }
        });
    }
}
