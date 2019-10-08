package com.twitter.challenge;

import org.assertj.core.data.Offset;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.assertj.core.api.Java6Assertions.within;
public class StandardDeviationTests {

    @Test
    public void testStandardDeviation() {
        final Offset<Float> precision = within(0.1f);
        List<Float> dataPoints = new ArrayList<>();
        dataPoints.add(16.83f);
        dataPoints.add(11.15f);
        dataPoints.add(14.2f);
        dataPoints.add(9.88f);
        dataPoints.add(19.19f);

        assertThat(StandardDeviation.standardDeviation(dataPoints)).isEqualTo(3.9f, precision);
    }
}
