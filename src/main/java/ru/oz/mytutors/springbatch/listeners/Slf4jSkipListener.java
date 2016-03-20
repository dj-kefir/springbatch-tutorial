package ru.oz.mytutors.springbatch.listeners;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.listener.SkipListenerSupport;
import org.springframework.stereotype.Component;

/**
 * Created by Igor Ozol
 * on 19.03.2016.
 * Eldorado LLC
 */
@Component("skipListener")
@Slf4j
public class Slf4jSkipListener<T, S> extends SkipListenerSupport<T, S> {

    @Override
    public void onSkipInRead(Throwable t) {
        log.warn("skipped item: {}",t.toString());
    }
}
