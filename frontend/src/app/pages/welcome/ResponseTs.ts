export interface ResponseTs {
  ecommerce: Ecommerce[];
  agg: Agg;
  total: number;
}

interface Agg {
  statues: Statue[];
  category: Statue[];
  quantityHistogram: QuantityHistogram[];
}

interface QuantityHistogram {
  docCount: number;
  key: number;
}

interface Statue {
  docCount: number;
  key: string;
}

interface Ecommerce {
  _id: number;
  quantity: number;
  price: string;
  name: string;
  description: string;
  categories: Category[];
  status: string;
  tags: string[];
}

interface Category {
  name: string;
}
