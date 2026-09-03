package constructors_and_java_keywords.class_problems;

public class Problem3 {

    static class BusRoute {

        private String routeCode;
        private String routeName;
        private int priority;

        public BusRoute(
            String routeCode,
            String routeName,
            int priority
        ) {
            this.routeCode = routeCode;
            this.routeName = routeName;
            this.priority = priority;
        }

        public BusRoute(
            String routeCode,
            String routeName
        ) {
            this(routeCode, routeName, 1);
        }

        public int compareTo(BusRoute other) {

            // Higher priority comes first.
            if (this.priority != other.priority) {
                return Integer.compare(
                    other.priority,
                    this.priority
                );
            }

            // Case-insensitive route-code comparison.
            int codeComparison =
                this.routeCode.compareToIgnoreCase(
                    other.routeCode
                );

            if (codeComparison != 0) {
                return codeComparison;
            }

            // Final deterministic tie-breaker.
            return this.routeName.compareToIgnoreCase(
                other.routeName
            );
        }

        public static BusRoute[] rankRoutes(
            BusRoute[] routes
        ) {

            BusRoute[] ranked =
                new BusRoute[routes.length];

            for (int i = 0; i < routes.length; i++) {
                ranked[i] = routes[i];
            }

            // Stable insertion sort.
            for (int i = 1; i < ranked.length; i++) {

                BusRoute current = ranked[i];
                int j = i - 1;

                while (j >= 0
                        && ranked[j].compareTo(current) > 0) {

                    ranked[j + 1] = ranked[j];
                    j--;
                }

                ranked[j + 1] = current;
            }

            return ranked;
        }
    }

    public static void main(String[] args) {

        BusRoute[] routes = {
            new BusRoute(
                "RT205L",
                "Airport Express",
                3
            ),
            new BusRoute(
                "rt201j",
                "City Central",
                4
            ),
            new BusRoute(
                "RT299T",
                "Night Service"
            )
        };

        BusRoute[] ranked =
            BusRoute.rankRoutes(routes);

        for (BusRoute route : ranked) {
            System.out.println(route.routeCode);
        }
    }
}