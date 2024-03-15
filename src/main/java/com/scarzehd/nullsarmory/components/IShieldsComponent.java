package com.scarzehd.nullsarmory.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import dev.onyxstudios.cca.api.v3.component.tick.ServerTickingComponent;

public interface IShieldsComponent extends Component, AutoSyncedComponent, ServerTickingComponent {
    double getCurrentShields();

    void setCurrentShields(double shields);

    int getCurrentRechargeDelay();

    void setCurrentRechargeDelay(int delay);

    void resetRechargeDelay();
}
