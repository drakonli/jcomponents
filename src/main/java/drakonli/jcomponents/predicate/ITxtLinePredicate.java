package drakonli.jcomponents.predicate;

import java.util.function.Predicate;

public interface ITxtLinePredicate extends Predicate<String>
{
    @Override
    boolean test(String line);
}
