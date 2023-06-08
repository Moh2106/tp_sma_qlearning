package sma;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainAgent extends Agent {

    List<Path> paths = new ArrayList<>();
    @Override
    protected void setup() {
        addBehaviour(new CyclicBehaviour() {
            @Override
            public void action() {
                ACLMessage receiver = receive();

                if (receiver!=null){
                    String[] message = receiver.getContent().split("=>");
                    Path path = new Path();
                    path.setRoutes(message[0]);
                    path.setIteration(Integer.parseInt(message[1]));
                    path.setLocalName(receiver.getSender().getLocalName());

                    paths.add(path);

                    System.out.println("***** Envoi d'un agent ****");
                    System.out.println("Agent : " +receiver.getSender().getLocalName() + "\n");
                    System.out.println(receiver.getContent());

                    Collections.sort(paths);

                    System.out.println("*** Affichage de l'agent qui a trouvé le chemin optimum ***");
                    System.out.println(paths.get(0).getRoutes() + " Iteration " + paths.get(0).getIteration() + " Sender : " + paths.get(0).getLocalName());
                }else block();
            }
        });
    }
}
