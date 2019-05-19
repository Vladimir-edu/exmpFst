package suits;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tests.FirstTaskPjOb;
import tests.FirstTaskSmplFast;
import tests.SecondTaskAPI;
import suits.MyCategories.*;

@RunWith(Categories.class)
@Categories.IncludeCategory(SecondT.class)
@Suite.SuiteClasses({FirstTaskPjOb.class, FirstTaskSmplFast.class, SecondTaskAPI.class})
public class SecondTask {
}
