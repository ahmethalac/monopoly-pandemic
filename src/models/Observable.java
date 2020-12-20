package models;

import controllers.observers.GameLogObserver;
import controllers.observers.Observer;

import java.util.ArrayList;
import java.util.List;

public class Observable {
    protected List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers){
            observer.update();
        }
    }

    public void notifyGameLogObserver(String changeType, double amount){
        for (Observer observer : observers){
            if (observer instanceof GameLogObserver){
                ((GameLogObserver)observer).update(changeType, amount);
            }
        }
    }
}
