package controllers.observers;

import models.Observable;

public abstract class Observer {
    protected Observable subject;
    public abstract void update();
}
