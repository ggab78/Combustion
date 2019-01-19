public class AirProperties {

        private final double molarMass;

        public static final double OXYGEN = 20.947;
        public static final double NITROGEN = 78.084;
        public static final double ARGON = 0.934;
        public static final double CO_2 = 0.035;

        public AirProperties() {

                this.molarMass = OXYGEN / 100 * 2 * PhysicalProperties.MOLARMASS_O +
                        NITROGEN / 100 * 2 * PhysicalProperties.MOLARMASS_N +
                        ARGON / 100 * PhysicalProperties.MOLARMASS_Ar +
                        CO_2 / 100 * (PhysicalProperties.MOLARMASS_C + 2 * PhysicalProperties.MOLARMASS_O);
        }

        public double getMolarMass() {
                return molarMass;
        }
}
