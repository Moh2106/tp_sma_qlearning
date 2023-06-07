# tp_sma_qlearning

L'algorithme Q-learning est une technique d'apprentissage par renforcement utilisée dans le domaine de l'intelligence artificielle. Il s'agit d'un algorithme d'apprentissage non supervisé qui permet à un agent d'apprendre à prendre des décisions optimales dans un environnement basé sur les récompenses.

Le Q-learning est utilisé dans les problèmes où un agent doit prendre des actions séquentielles pour maximiser une récompense cumulative à long terme. L'agent explore l'environnement, prend des actions et observe les récompenses résultantes. En utilisant ces informations, il met à jour une fonction de valeur appelée la fonction Q, qui attribue à chaque état-action une valeur correspondant à la récompense attendue.

L'algorithme Q-learning suit un processus itératif pour mettre à jour la fonction Q. À chaque étape, l'agent choisit une action à partir de l'état actuel en utilisant une politique d'exploration-exploitation. Après avoir effectué une action, l'agent reçoit une récompense et passe à l'état suivant. Ensuite, il met à jour la valeur Q de l'état-action actuel en utilisant la formule de mise à jour de Q-learning :

Q(s, a) = Q(s, a) + α * (r + γ * maxQ(s', a') - Q(s, a))

où :

Q(s, a) est la valeur de la fonction Q pour l'état s et l'action a.
α est le taux d'apprentissage, qui contrôle dans quelle mesure les nouvelles informations sont prises en compte lors de la mise à jour de Q.
r est la récompense reçue après avoir effectué l'action a à partir de l'état s.
γ est le facteur d'escompte, qui pondère l'importance des récompenses à court terme par rapport aux récompenses à long terme.
maxQ(s', a') est la valeur maximale de la fonction Q pour le prochain état s' et toutes les actions possibles a'.
L'algorithme Q-learning continue d'explorer et d'apprendre de l'environnement en effectuant des itérations jusqu'à ce qu'une politique optimale soit trouvée, c'est-à-dire une politique qui maximise la récompense cumulative attendue.

Il convient de noter que l'algorithme Q-learning est basé sur l'hypothèse de Markov, ce qui signifie qu'il suppose que l'environnement est entièrement observable et que les états futurs dépendent uniquement de l'état actuel et de l'action prise.
