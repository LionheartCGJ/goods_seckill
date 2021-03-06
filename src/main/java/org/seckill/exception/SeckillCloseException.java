package org.seckill.exception;

/**
 * 秒杀关闭异常
 * 
 * @author CGJ
 *
 */
public class SeckillCloseException extends SeckillException {

    private static final long serialVersionUID = -850110949024776554L;

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }

}
