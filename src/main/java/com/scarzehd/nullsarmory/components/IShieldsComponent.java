package com.scarzehd.nullsarmory.components;

import org.ladysnake.cca.api.v3.component.Component;
import org.ladysnake.cca.api.v3.component.sync.AutoSyncedComponent;
import org.ladysnake.cca.api.v3.component.tick.ServerTickingComponent;

public interface IShieldsComponent extends Component, AutoSyncedComponent, ServerTickingComponent {
    double getCurrentShields();

    void setCurrentShields(double shields);

    int getCurrentRechargeDelay();

    void setCurrentRechargeDelay(int delay);

    void resetRechargeDelay();

    boolean isCharging();
}
