public class RequiredAir {

    private final double remainingOxygen;
    private final GasProperties gasProp;
    private final double so2ratio = 0.95;


    public RequiredAir(GasProperties gas, double remainingOxygen) {
        this.remainingOxygen = remainingOxygen / 100;//value in percent
        this.gasProp = gas;
    }

    public double combustionCO2product() {
        return gasProp.combustibleCarbon();
        //return number of C02 moles per one mol of fuel gas
    }

    public double combustionH2Oproduct() {
        return 0.5 * gasProp.combustibleHydrogen();
        //return number of H2O moles per one mol of fuel gas
    }

    public double combustionSO2product() {
        return gasProp.combustibleSulfur() * so2ratio;
        //return number of SO2 moles per one mol of fuel gas
    }

    public double combustionSO3product() {
        return gasProp.combustibleSulfur() * (1 - so2ratio);
        //return number of SO3 moles per one mol of fuel gas
    }

    public double stechiometricRemainingN2() {
        return stechiometricOxygenDemand() * AirProperties.NITROGEN / AirProperties.OXYGEN;
        //returns no of mols N2 per one mol of fuel gas
    }

    public double stechiometricRemainingCO2() {
        return stechiometricOxygenDemand() * AirProperties.CO_2 / AirProperties.OXYGEN;
        //returns no of mols CO2 per one mol of fuel gas
    }

    public double stechiometricRemainingAr() {
        return stechiometricOxygenDemand() * AirProperties.ARGON / AirProperties.OXYGEN;
        //returns no of mols Ar per one mol of fuel gas
    }

    public double leanBurnRemainingO2() {
        double v = (remainingOxygen * stechiometricExhaustGasFlow())/
                (1 - remainingOxygen * (1 + AirProperties.NITROGEN / AirProperties.OXYGEN + AirProperties.ARGON / AirProperties.OXYGEN + AirProperties.CO_2 / AirProperties.OXYGEN));
        return v;
        //returns no of mols O2 per one mol of fuel gas
    }

    public double leanBurnRemainingN2() {
        return (stechiometricOxygenDemand() + leanBurnRemainingO2()) * AirProperties.NITROGEN / AirProperties.OXYGEN;
        //returns no of mols N2 per one mol of fuel gas
    }

    public double leanBurnRemainingCO2() {
        return (stechiometricOxygenDemand() + leanBurnRemainingO2()) * AirProperties.CO_2 / AirProperties.OXYGEN;
        //returns no of mols CO2 per one mol of fuel gas
    }

    public double leanBurnRemainingAr() {
        return (stechiometricOxygenDemand() + leanBurnRemainingO2()) * AirProperties.ARGON / AirProperties.OXYGEN;
        //returns no of mols Ar per one mol of fuel gas
    }


    public double stechiometricOxygenDemand() {
        double CO2 = 2 * gasProp.combustibleCarbon();
        double H20 = 0.5 * gasProp.combustibleHydrogen();
        double SO2 = 2 * gasProp.combustibleSulfur() * so2ratio;
        double SO3 = 3 * gasProp.combustibleSulfur() * (1 - so2ratio);
        return (CO2+H20+SO2+SO3)/2;
        //returns number of O2 mols per one mol of gas.
    }

    public double stechiometricAirDemand() {
        //returns number of Air mols per one mol of gas.
        return stechiometricOxygenDemand() * 100 / AirProperties.OXYGEN;
    }

    public double stechiometricExhaustGasFlow() {
        double v = combustionCO2product() + combustionH2Oproduct() +
                combustionSO2product() + combustionSO3product() +
                stechiometricRemainingN2() + stechiometricRemainingCO2() + stechiometricRemainingAr();
        return v;
        //returns no of mol of exhaust gas per one mol of fuel gas
    }
    public double leanBurnExhaustGasFlow() {
        double v = combustionCO2product() + combustionH2Oproduct() +
                combustionSO2product() + combustionSO3product() +
                leanBurnRemainingO2() + leanBurnRemainingN2() +
                leanBurnRemainingAr() + leanBurnRemainingCO2();
        return v;
        //returns no of mol of exhaust gas per one mol of fuel gas
    }

}
