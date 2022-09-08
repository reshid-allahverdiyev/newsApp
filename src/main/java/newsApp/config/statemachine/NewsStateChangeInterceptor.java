package newsApp.config.statemachine;

import lombok.RequiredArgsConstructor;
import newsApp.entity.NewsEntity;
import newsApp.repository.mysql.NewsRepository;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class NewsStateChangeInterceptor extends StateMachineInterceptorAdapter<NewsState, NewsEvent> {
    private final NewsRepository postRepository;

    @Override
    public void preStateChange(State<NewsState, NewsEvent> state, Message<NewsEvent> message, Transition<NewsState, NewsEvent> transition, StateMachine<NewsState, NewsEvent> stateMachine, StateMachine<NewsState, NewsEvent> rootStateMachine) {
        Optional.ofNullable(message).ifPresent(msg -> {
            Optional.ofNullable((Long) msg.getHeaders().getOrDefault("blog_id", -1L)).ifPresent(blogId -> {
                NewsEntity news = postRepository.findById(blogId).get();
                news.setState(state.getId());
                postRepository.save(news);
            });
        });
    }
}
