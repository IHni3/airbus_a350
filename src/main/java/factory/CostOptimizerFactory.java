package factory;

import configuration.Configuration;

public class CostOptimizerFactory {

    public static Object build() {
        return FactoryUtils.build("CostOptimizerFactory",
                Configuration.instance.pathToCostOptimizerJavaArchive,
                "CostOptimizer");
    }
}