package io.github.e1s.components.views;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ViewsServiceImpl implements ViewsService {

    private ViewsRepository viewsRepository;

    public ViewsServiceImpl(ViewsRepository viewsRepository) {
        this.viewsRepository = viewsRepository;
    }

    @Override
    @Transactional
    public void increaseViews(Long id) {
        if (id == null) {
            return;
        }
        viewsRepository.findByIdAndIncreaseViewsByOne(id);
    }
}
