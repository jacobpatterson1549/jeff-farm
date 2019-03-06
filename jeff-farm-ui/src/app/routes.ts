import { CrudModule } from './crud/crud.module';

export const FARMS_ROUTES = [
    {
        path: 'farms',
        loadChildren: crudEntryPoint,
        pathMatch: 'prefix',
    }
];
export const HIVES_ROUTES = [
    {
        path: 'farms/:farm_id/hives',
        loadChildren: crudEntryPoint,
        pathMatch: 'prefix',
    }
];
export const HIVE_INSPECTIONS_ROUTES = [
    {
        path: 'farms/:farm_id/hives/:hive_id/hiveInspections',
        loadChildren: crudEntryPoint,
        pathMatch: 'prefix',
    }
];

function crudEntryPoint() {
    return CrudModule;
}