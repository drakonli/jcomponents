package drakonli.jcomponents.predicate;

import java.util.function.Predicate;

public interface TxtLinePredicateInterface extends Predicate<String>
{
    @Override
    boolean test(String line);
}
