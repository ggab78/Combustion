public class Main {

    public static void main(String[] args) {

        GasComposition gas = new GasComposition();
        gas.createGasComposition();

//        for(Component c : gas.getComponents()){
//            System.out.println(c);
//        }
        for(Component c : gas.getComponents()){
            System.out.println(c.getCombustibleHydrogen());
        }

        GasProperties gasProp = new GasProperties(gas);

        RequiredAir air = new RequiredAir(gasProp, 10.0);

        System.out.println(gasProp.combustibleCarbon()+ " comb C");
        System.out.println(gasProp.combustibleHydrogen()+" comb H");
        System.out.println(gasProp.combustibleSulfur()+" comb S");
        System.out.println(air.combustionH2Oproduct()+" comustion H20");
        System.out.println(air.combustionCO2product()+" comustion C02");
        System.out.println(air.stechiometricOxygenDemand()+ " stechiometric Oxygen demand");
        System.out.println(air.stechiometricAirDemand()+ " stechiometric Air demand");
        System.out.println(air.stechiometricExhaustGasFlow()+ " stechiometric exhaust gas flow");
        System.out.println(air.leanBurnRemainingO2()+ " mol of remaining oxygen after lean burn");
        System.out.println(air.leanBurnExhaustGasFlow()+ " lean burn exhaust gas flow");
        System.out.println(air.leanBurnRemainingO2()/air.leanBurnExhaustGasFlow()+" ratio");
    }

}
