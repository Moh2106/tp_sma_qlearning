package sma;

import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.StaleProxyException;

public class AgentContainer {
    public static void main(String[] args) throws StaleProxyException {
        Runtime runtime = Runtime.instance();
        ProfileImpl profile = new ProfileImpl();
        profile.setParameter(ProfileImpl.MAIN_HOST, "localhost");
        jade.wrapper.AgentContainer agentContainer = runtime.createAgentContainer(profile);

        AgentController qlearning = agentContainer.createNewAgent("qlearning3", "sma.QLearningAgent", new Object[]{});
//        AgentController main = agentContainer.createNewAgent("main", "sma.MainAgent", new Object[]{});

        qlearning.start();
//        main.start();
    }
}
