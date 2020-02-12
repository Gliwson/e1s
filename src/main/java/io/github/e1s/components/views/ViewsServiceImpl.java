package io.github.e1s.components.views;

import org.springframework.stereotype.Service;

@Service
public class ViewsServiceImpl implements ViewsService {

    private ViewsRepository viewsRepository;

    public ViewsServiceImpl(ViewsRepository viewsRepository) {
        this.viewsRepository = viewsRepository;
    }

    @Override
    public void increaseViews(Long id) {
        if (id == null) {
            return;
        }
        viewsRepository.findByIdAndIncreaseViewsByOne(id);
    }
}
