/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { InteliManagementTestModule } from '../../../test.module';
import { ProductImDetailComponent } from 'app/entities/product-im/product-im-detail.component';
import { ProductIm } from 'app/shared/model/product-im.model';

describe('Component Tests', () => {
    describe('ProductIm Management Detail Component', () => {
        let comp: ProductImDetailComponent;
        let fixture: ComponentFixture<ProductImDetailComponent>;
        const route = ({ data: of({ product: new ProductIm(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [InteliManagementTestModule],
                declarations: [ProductImDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(ProductImDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(ProductImDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.product).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
