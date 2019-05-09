package cn.ce.gateway.open.listener;

import cn.ce.gateway.open.config.CustomRouteLocator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.bus.event.RefreshRemoteApplicationEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class RouteRefreshListener
			implements ApplicationListener<ApplicationEvent> {
		@Autowired
		private CustomRouteLocator customRouteLocator;


		@Override
		public void onApplicationEvent(ApplicationEvent event) {
			if (event instanceof RefreshRemoteApplicationEvent) {
				customRouteLocator.doRefresh();
			}
		}
	}