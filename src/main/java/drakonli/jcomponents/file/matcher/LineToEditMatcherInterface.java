package drakonli.jcomponents.file.matcher;

import drakonli.jcomponents.matcher.MatcherInterface;

public interface LineToEditMatcherInterface extends MatcherInterface<String>
{
    @Override
    Boolean match(String line);
}
