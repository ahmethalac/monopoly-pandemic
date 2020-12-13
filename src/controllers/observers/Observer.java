package controllers.observers;

import storage.models.Observable;

public abstract class Observer {
    protected Observable subject;
    public abstract void update();
}
