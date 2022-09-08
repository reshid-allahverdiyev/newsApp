package newsApp.config.statemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class NewsStateMachineConfig extends EnumStateMachineConfigurerAdapter<NewsState, NewsEvent> {
    @Override
    public void configure(StateMachineStateConfigurer<NewsState, NewsEvent> states) throws Exception {
        states.withStates().initial(NewsState.WAITING_FOR_REVIEW)
                .states(EnumSet.allOf(NewsState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<NewsState, NewsEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(NewsState.WAITING_FOR_REVIEW)
                .target(NewsState.IN_REVIEW)
                .event(NewsEvent.GET_FOR_REVIEW)
                .and()
                .withExternal()
                .source(NewsState.IN_REVIEW)
                .target(NewsState.APPROVED)
                .event(NewsEvent.APPROVE)
                .and()
                .withExternal()
                .source(NewsState.IN_REVIEW)
                .target(NewsState.REJECTED)
                .event(NewsEvent.REJECT)
                .and()
                .withExternal()
                .source(NewsState.APPROVED)
                .target(NewsState.REJECTED)
                .event(NewsEvent.REJECT);
    }
}
