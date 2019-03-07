import { CrudModule } from './crud/crud.module';

export const FARMS_ROUTES = [
    {
        path: 'farms',
        loadChildren: '../crud/crud.module#CrudModule',
        pathMatch: 'prefix',
    }
];
export const HIVES_ROUTES = [
    {
        path: 'farms/:farm_id/hives',
        loadChildren: '../crud/crud.module#CrudModule',
        pathMatch: 'prefix',
    }
];
export const HIVE_INSPECTIONS_ROUTES = [
    {
        path: 'farms/:farm_id/hives/:hive_id/hiveInspections',
        loadChildren: '../crud/crud.module#CrudModule',
        pathMatch: 'prefix',
    }
];
