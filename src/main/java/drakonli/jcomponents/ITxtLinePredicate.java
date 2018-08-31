package drakonli.jcomponents;

import java.util.function.Predicate;

public interface ITxtLinePredicate extends Predicate<String>
{
    @Override
    boolean test(String line);
}
