// Core UI
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatSortModule } from '@angular/material/sort';
// User interaction
import { MatMenuModule } from '@angular/material/menu';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatButtonModule } from '@angular/material/button';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatButtonToggleModule } from '@angular/material/button-toggle';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { MatSliderModule } from '@angular/material/slider';
// Rendering
import { MatBadgeModule } from '@angular/material/badge';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressBarModule } from '@angular/material/progress-bar';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatRippleModule } from '@angular/material/core'; 
// Visualization
import { MatListModule } from '@angular/material/list'; 
import { MatTableModule } from '@angular/material/table';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatCardModule } from '@angular/material/card';
import { MatChipsModule } from '@angular/material/chips'; 


export function getAngularMaterialModules(): readonly any[] {

    return [
        MatSidenavModule,
        MatToolbarModule,
        MatSortModule,
        MatMenuModule,
        MatSnackBarModule,
        MatButtonModule,
        MatSlideToggleModule,
        MatButtonToggleModule,
        MatSelectModule,
        MatRadioModule,
        MatSliderModule,
        MatBadgeModule,
        MatIconModule,
        MatProgressBarModule,
        MatProgressSpinnerModule,
        MatTooltipModule,
        MatRippleModule,
        MatListModule,
        MatTableModule,
        MatExpansionModule,
        MatPaginatorModule,
        MatCardModule,
        MatChipsModule,
    ]
}