package assignment3;

// @authors: Annika Baldi, Milan Proell

import org.junit.Assert;
import org.junit.Test;

public class RocketScience {

    public static void main(String[] args) {
        Rocket rocket1 = new Rocket();
        System.out.println("deduced payload:" + fewestFailures(rocket1));
        System.out.println("attempts needed:" + rocket1.getTries());

        System.out.println("----");

        Rocket rocket2 = new Rocket();
        System.out.println("deduced payload:" + fewestAttemptsLogN(rocket2));
        System.out.println("attempts needed:" + rocket2.getTries());

        System.out.println("----");

        Rocket rocket3 = new Rocket();
        System.out.println("deduced payload:" + fewestAttemptsLogK(rocket3));
        System.out.println("attempts needed:" + rocket3.getTries());

        System.out.println("----");

        Rocket rocket4 = new Rocket();
        System.out.println("deduced payload:" + fewestAttemptsTwoRockets(rocket4));
        System.out.println("attempts needed:" + rocket4.getTries());
    }

    public static int fewestFailures(Rocket rocket) {
        for (int i = 2; i <= 1000; i++) { // zählt einfach durch, muss erst ab 2 gestartet werden da 1 mit erster iteration geprüft
            if (!rocket.canBear(i)) {
                return i - 1; // wenn es nicht mehr geht, wird der letzte Wert zurückgegeben.
            }

        }
        return -1;
    }

    public static int fewestAttemptsLogN(Rocket rocket) {
        int mitte = -1; // meckert sonst
        int links = 0;
        int rechts = 1000;

        while (links <= rechts) { //binäre suche, die max O(log(n)) braucht auf diesen fall angepasst
            //läuft dadurch bis zum ende und braucht jedesmal log(n) versuche
            mitte = links + ((rechts - links) / 2); // mitte wird berechnet
            if (!rocket.canBear(mitte)) {
                rechts = mitte - 1; // im linken Abschnitt weitersuchen
            } else {
                links = mitte + 1; // im rechten Abschnitt weitersuchen
            }
        }
        return rechts; // Am Ende wird der letzte rechte Wert ausgegeben, da dieser der Lösung entspricht
    }

    public static int fewestAttemptsLogK(Rocket rocket) {
        int j;
        for (j = 2; rocket.canBear(j); j = j * 2) ; // exponentielle suche
        if (j > 1000) j = 1001;
        int mitte = -1; // meckert sonst
        int links = j / 2;
        int rechts = j - 1;

        while (links <= rechts) { //binäre suche, die max O(log(n)) braucht auf diesen fall angepasst
            //läuft dadurch bis zum ende und braucht jedesmal log(n) versuche
            mitte = links + ((rechts - links) / 2); // mitte wird berechnet
            if (!rocket.canBear(mitte)) {
                rechts = mitte - 1; // im linken Abschnitt weitersuchen
            } else {
                links = mitte + 1; // im rechten Abschnitt weitersuchen
            }
        }
        return rechts; // Am Ende wird der letzte rechte Wert ausgegeben, da dieser der Lösung entspricht
    }

    public static int fewestAttemptsTwoRockets(Rocket rocket) {
        int sum = 45;
        for (int i = 44; i > 0; i = i - 1) { //Gaußsche Summenformel
            if (rocket.canBear(sum)) { // Algorithmus basiert auf dem Eierproblem (https://www.spin.de/forum/432/-/508e), man berechnet den optimalen startwert
                // über die Gaußsche Summenformel was bei 1000 die 44 ist da (44^2+44)/2 = 990 (wegen teständerung 45 nicht möglich)
                // über die zahl 45 und die for schleife, wird dann die Anzahl
                // der Versuche bei max(srqt(n)) gehalten
                // und durch das von unten nach oben gehen, gehen somit immer maximal 2 Raketen kaputt
                // Das ist die optimale Lösung, die wir gefunden haben
                // -> Worst case 45 Versuche (aber anscheinend schlechter als der Test)
                sum = sum + i;
            } else {
                int j;
                for (j = sum - i; j < sum; j++) {
                    if (!rocket.canBear(j)) {
                        return j - 1;
                    }
                }
                return j - 1;
            }
        }
        return -1;
    }


    @Test
    public void binarySearchTest() {
        for (int i = 1; i <= 1000; i++) {
            Rocket test = new Rocket(i);

            Assert.assertEquals(i, fewestAttemptsLogN(test));
            Assert.assertEquals(i, fewestAttemptsLogK(test));
            Assert.assertEquals(i, fewestAttemptsTwoRockets(test));
        }
    }
}