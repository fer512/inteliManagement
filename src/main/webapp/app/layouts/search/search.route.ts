import { Route } from '@angular/router';

import { SearchComponent } from './search.component';

export const searchRoute: Route = {
    path: '',
    component: SearchComponent,
    outlet: 'search'
};
