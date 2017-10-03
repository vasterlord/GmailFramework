package com.epam.lab.gmailframework.utils.testreporting;

import io.qameta.allure.Step;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;


public class TestNGReportAppender extends AppenderSkeleton
{

    @Override
    protected synchronized void append(final LoggingEvent event)
    {
        Reporter.log(eventToString(event));
    }

    private String eventToString(final LoggingEvent event)
    {
        final StringBuilder result = new StringBuilder(layout.format(event));

        if(layout.ignoresThrowable())
        {
            final String[] s = event.getThrowableStrRep();
            if (s != null)
            {
                for (final String value : s)
                {
                    result.append(value).append(Layout.LINE_SEP);
                }
            }
        }
        return result.toString();
    }

    @Override
    public void close()
    {
    }

    @Override
    public boolean requiresLayout()
    {
        return true;
    }
}