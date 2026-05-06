package fun.imiku.napcat4j.component;

import fun.imiku.napcat4j.config.NapCatApiProperties;
import org.springframework.stereotype.Component;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class ConcurrencyLimiter {
    private final Semaphore semaphore;

    public ConcurrencyLimiter(NapCatApiProperties properties) {
        this.semaphore = new Semaphore(properties.getBotMaxEventConcurrency(), true);
    }

    /**
     * 尝试获取一个全局 bot 并发许可
     *
     * @return 需要释放的许可；如果已经达到并发上限，则返回 null
     */
    public Permit obtain() {
        if (!semaphore.tryAcquire()) {
            return null;
        }
        return new Permit(semaphore);
    }

    /**
     * 释放由 obtain 获取到的并发许可
     */
    public void release(Permit permit) {
        if (permit != null) {
            permit.release();
        }
    }

    public static final class Permit implements AutoCloseable {
        private final Semaphore semaphore;
        private final AtomicBoolean released = new AtomicBoolean(false);

        private Permit(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        public void release() {
            if (released.compareAndSet(false, true)) {
                semaphore.release();
            }
        }

        @Override
        public void close() {
            release();
        }
    }
}
